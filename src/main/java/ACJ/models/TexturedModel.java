package ACJ.models;

import ACJ.texture.Texture;

public class TexturedModel extends Model {
    private Texture texture;

    public TexturedModel(Model model, Texture texture){
        super(model.getVao());
        this.texture = texture;
    }

    public void bind(){
        super.bind();
        
    }

    public void unbind(){
        super.unbind();
    }

    public Texture getTexture(){
        return texture;
    }
    
}
