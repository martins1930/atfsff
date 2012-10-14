import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.io.*;
import java.nio.file.*;
import static java.nio.file.LinkOption.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.attribute.*;
import java.util.*;
import org.gradle.tooling.GradleConnector ;
import org.gradle.tooling.ProjectConnection ;

/*
msteffen
*/
class ListenFiles extends DefaultTask {
	def dir = '.'
	def projectBase = null
	def recursive = true
	def trace = false;
	private WatchService watcher;
	private Map<WatchKey,Path> keys;
  
	@SuppressWarnings("unchecked")
    	static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        	return (WatchEvent<T>)event;
    	}

    	/**
     		* Register the given directory with the WatchService
     	*/
    	def registerSingle(Path dir) throws IOException {
        	WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        	if (trace) {
            		Path prev = keys.get(key);
            		if (prev == null) {
                		System.out.format("register: %s\n", dir);
            		} else {
                		if (!dir.equals(prev)) {
                    			System.out.format("update: %s -> %s\n", prev, dir);
                		}
            		}		
        	}
        	keys.put(key, dir);
    	}

	/**
	* Register the given directory, and all its sub-directories, with the
	* WatchService.
	*/
	def registerAll(final Path start) throws IOException {
	    // register directory and sub-directories
	    Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
		    throws IOException
		{
		    registerSingle(dir);
		    return FileVisitResult.CONTINUE;
		}
	    });
	}
	
	
	
	/**
	* Creates a WatchService and registers the given directory
	*/
	def configListenFiles(Path dir, boolean recursive) throws IOException {
	    this.watcher = FileSystems.getDefault().newWatchService();
	    this.keys = new HashMap<WatchKey,Path>();
	    this.recursive = recursive;

	    if (recursive) {
		System.out.format("Scanning %s ...\n", dir);
		registerAll(dir);
		System.out.println("Done.");
	    } else {
		registerSingle(dir);
	    }

	    // enable trace after initial registration
	    this.trace = true;
	}	
	
	
	/**
	  * Process all events for keys queued to the watcher
	  */
	void processEvents() {
	    println "Hola processEvents" 
	    for (;;) {

		// wait for key to be signalled
		WatchKey key;
		try {
		    key = watcher.take();
		} catch (InterruptedException x) {
		    return;
		}

		Path dir = keys.get(key);
		if (dir == null) {
		    System.err.println("WatchKey not recognized!!");
		    continue;
		}

		for (WatchEvent<?> event: key.pollEvents()) {
		    WatchEvent.Kind kind = event.kind();

		    // TBD - provide example of how OVERFLOW event is handled
		    if (kind == OVERFLOW) {
			continue;
		    }

		    // Context for directory entry event is the file name of entry
		    WatchEvent<Path> ev = cast(event);
		    Path name = ev.context();
		    Path child = dir.resolve(name);

		    
		    if (child.toString().endsWith(".txt") || child.toString().endsWith(".java")) {
			// print out event
			System.out.format("change in: %s: %s\n", event.kind().name(), child);	
			ProjectConnection connection = GradleConnector.newConnector()
							      .forProjectDirectory(projectBase)
							      .connect();
							      
			try {
			    connection.newBuild().forTasks("dumymartin").run();
			} catch (Exception e){
			    System.out.println("Error al ejecutar tarea: "+e.getMessage()) ;
			} finally {
			    connection.close();
			}							      
						
		      
		    }

		    // if directory is created, and watching recursively, then
		    // register it and its sub-directories
		    if (recursive && (kind == ENTRY_CREATE) && child.toString().indexOf(".")==-1) {
			try {
			    if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
				registerAll(child);
			    }
			} catch (IOException x) {
			    // ignore to keep sample readbale
			}
		    }
		}

		// reset key and remove from set if directory no longer accessible
		boolean valid = key.reset();
		if (!valid) {
		    keys.remove(key);

		    // all directories are inaccessible
		    if (keys.isEmpty()) {
			break;
		    }
		}
	    }
	}	

	@TaskAction
	def runListener() {

		// register directory and process its events
		Path dirp = Paths.get(dir);
		configListenFiles(dirp, recursive)
		processEvents();


		def listen = "Scaning directory=${dir} with option recursive= ${recursive}"  
		println listen ;
	}	
}
