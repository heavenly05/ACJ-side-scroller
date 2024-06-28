package ACJ.components;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.Set;
import java.util.HashSet;

public class Vao {

    private static final int BYTES_PER_FLOAT = 4;

    private static Set<Vao> vaos = new HashSet<Vao>();

    private Vbo[] vbos = new Vbo[16];
    private Ebo ebo;
    private int id;
    private int indexCount;
    private int vertexCount;

    public Vao(int vertexCount){
        this.vertexCount = vertexCount;
        create();
    }

    private void create(){
        id = GL30.glGenVertexArrays();
        vaos.add(this);
    }

    public void bind(){
        GL30.glBindVertexArray(id);
    }

    public void unbind(){
        GL30.glBindVertexArray(0);
    }

    public void bindAttribute(int attrNum, int attrSize, int dataType, boolean normalized, int bytesPerVertex, int offset){
        GL30.glVertexAttribPointer(attrNum, attrSize, dataType, normalized, bytesPerVertex, offset);
    }

    public void bindVbos(){
        for(Vbo vbo : vbos){
            if(vbo != null)
                vbo.bind();
        }
        if(ebo != null){
            ebo.bind();
        }
    }
    
    public void unbindVbos(){
        for(Vbo vbo : vbos){
            if(vbo != null)
                vbo.unbind();
        }
        if(ebo != null){
            ebo.unbind();
        }
    }

    public Ebo createIndexBuffer(int[] data){
        ebo = new Ebo(data, GL15.GL_ELEMENT_ARRAY_BUFFER, GL15.GL_STATIC_DRAW, GL11.GL_INT);
        ebo.bind();
        ebo.store();
        ebo.unbind();
        indexCount = data.length;
        return ebo;
    }

    public Vbo createAttribute(int attrNum, int stride, float[] data){
        Vbo vbo = new Vbo(data, GL15.GL_ARRAY_BUFFER, stride, GL15.GL_STATIC_DRAW, GL11.GL_FLOAT);
        vbo.bind();
        vbo.store();
        bindAttribute(attrNum, stride, vbo.getDataType(), false, stride * BYTES_PER_FLOAT, 0);
        vbo.unbind();
        vbos[attrNum] = vbo;
        return vbo;
    }

    public void enableAttribute(int attrNum){
        GL20.glEnableVertexAttribArray(attrNum);
    }

    public void disableAttribute(int attrNum){
        GL20.glDisableVertexAttribArray(attrNum);
    }

    public void enableAttributes(){
        for(int i = 0; i < vbos.length; i++){
            Vbo vbo = vbos[i];
            if(vbo != null){
                enableAttribute(i);
            }
        }
    }

    public void disableAttributes(){
        for(int i = 0; i < vbos.length; i++){
            Vbo vbo = vbos[i];
            if(vbo != null){
                disableAttribute(i);
            }
        }
    }

    public Ebo getEbo(){
        return ebo;
    }

    public int getIndexCount(){
        return indexCount;
    }

    public int[] findStrides(float[]...datas){
        int[] strides = new int[datas.length];
        for(int i = 0; i < datas.length; i++){
            strides[i] = datas[i].length / vertexCount;
        }
        return strides;
    }

    public void storeData(int[] indices, float[]...datas){
        storeData(indices, findStrides(datas), datas);
    }

    public void storeData(int[] indices, int[] strides, float[]...datas){
        for(int i = 0; i < datas.length; i ++){
            createAttribute(i, strides[i], datas[i]);
        }
        createIndexBuffer(indices);
    }

    public void delete(){
        GL30.glDeleteVertexArrays(id);
        for(Vbo vbo : vbos){
            if(vbo != null){
                vbo.delete();
            }
        }
        if(ebo != null){
            ebo.delete();
        }
    }

    public void cleanUp(){
        for(Vao vao : vaos){
            vao.delete();
        }
    }

    

    //its k just do it
}//for me to run the gradle build i need to downgrade my jdk to 1.17. im not sure if it requires a restart