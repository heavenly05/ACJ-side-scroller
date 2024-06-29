package logic;

import java.util.HashMap;
import java.util.Map;

import ACJ.models.Model;
import ACJ.models.TexturedModel;
import ACJ.texture.Texture;

public class DataCenter {

    private Map<String, Texture> textures = new HashMap<String, Texture>();
    private Map<String, Model> models = new HashMap<String, Model>();
    private Map<String, TexturedModel> texturedModels = new HashMap<String, TexturedModel>();

    public void addTexture(String name, Texture texture){
        add(textures, name, texture);
    }

    public Texture getTexture(String name){
        return textures.get(name);
    }

    public void addModel(String name, Model model){
        add(models, name, model);
    }

    public Model getModel(String name){
        return models.get(name);
    }

    public void addTexturedModel(String name, TexturedModel texturedModel){
        add(texturedModels, name, texturedModel);
    }

    public TexturedModel getTexturedModel(String name){
        return texturedModels.get(name);
    }

    public <U> void add(Map<String, U> map, String name, U value){
        if(!map.containsValue(value)){
            map.put(name, value);
        }
    }
    
}
