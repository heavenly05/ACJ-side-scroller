package ACJ;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;

import ACJ.render.FinalRenderer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.util.function.Consumer;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;


/**
 * Class for Instansiating/Creating windows
 * @author Eletra & Heaven
 */
public class Window {

    private int height, width;
    private long address;
    private String title;
    private boolean cursorLocked;

    public Window(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
    }
   
    public void init(){
        //If glfw cannot innit throw
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        //glfw window settings
        glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_SAMPLES, 4);
        //3.3 is the most modern version, 3.2 is the minimum version for macOS
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
        //allows the use of core versions in shader files
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		address = glfwCreateWindow(width, height, "WINDOW", NULL, NULL);
        //check if window was successfully created, if not throw
		if(address == NULL) {
			throw new RuntimeException("Failed to create the GLFW window");
		}
        //make current window the main window
		glfwMakeContextCurrent(address);
		glfwSwapInterval(1);
		GLCapabilities.initialize();
		glfwShowWindow(address);
		//lockCursor(); will enable after creating an esc key
    }

    public void updateWindow(){
        glfwPollEvents();
        glfwSwapBuffers(address);
    }

    public void close(){
        unlockCursor();
        glfwDestroyWindow(address);
    }

    public void run(Runnable action){
        customLoop.accept(action);

    }
    //this should render a black window
    Consumer<Runnable> customLoop = (action) -> {
        while(!GLFW.glfwWindowShouldClose(address)){
            //weird stuff
            clear(new Vector3f(0, 1, 1));
            action.run();
            updateWindow();
        }
    };
    public void lockCursor(){
        cursorLocked = true;
        glfwSetInputMode(address, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public void unlockCursor(){
        cursorLocked = false;
        glfwSetInputMode(address, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public long getAddress(){
        return address;
    }

    public void clear(Vector3f color){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(color.x, color.y, color.z, 1);
    }
    
}