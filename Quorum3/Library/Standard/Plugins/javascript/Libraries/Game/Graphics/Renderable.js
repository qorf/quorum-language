function plugins_quorum_Libraries_Game_Graphics_Renderable_() 
{
    /*
     The publicly used properties of this class are:
        this.camera - A Quorum Camera object
        this.shader - A plugin Shader object
     */
    
    this.SetNative$quorum_Libraries_Game_Graphics_Renderable = function(renderable) 
    {
        this.shader = renderable.plugin_.shader;
    };
    
    this.GetCamera = function() 
    {
        return this.camera;
    };
}
