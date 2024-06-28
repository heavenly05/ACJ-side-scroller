package ACJ.components;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

public class Ebo {

    private int id;
    private int type, drawType, dataType;
    private IntBuffer data;

    public Ebo(int[] data, int type, int drawType, int dataType){
        this.type = type;
        this.drawType = drawType;
        this.dataType = dataType;
        setData(data);
        create();
        store();
    }

    private void create(){
        id = GL15.glGenBuffers();
    }

    public void store(){
        store(this.data);
    }

    public void store(IntBuffer buffer){
        GL15.glBufferData(type, buffer, drawType);
    }

    public void bind(){
        GL15.glBindBuffer(type, id);
    }

    public void unbind(){
        GL15.glBindBuffer(type, 0);
    }

    public void delete(){
        GL15.glDeleteBuffers(id);
    }

    public IntBuffer getData(){
        return data;
    }

    public int getSize(){
        return data.capacity();
    }

    public int getType(){
        return type;
    }

    public int getDrawType(){
        return drawType;
    }

    public int getDataType(){
        return dataType;
    }

    /**
     * simply a setter for the data floatbuffer
     * 
     * it is useless until I add the add(float[] data) functions
     * @param buffer
     */
    public void setData(IntBuffer buffer){
        this.data = buffer;
    }

    public void setData(int[] data){
        this.data = BufferUtils.createIntBuffer(data.length);
        this.data.put(data);
        this.data.flip();
    }

    public void setType(int type){
        this.type = type;
    }

    public void setDrawType(int drawType){
        this.drawType = drawType;
    }

    public void setDataType(int dataType){
        this.dataType = dataType;
    }
    
}
