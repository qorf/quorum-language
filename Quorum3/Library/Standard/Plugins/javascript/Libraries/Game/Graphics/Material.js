function plugins_quorum_Libraries_Game_Graphics_Material_() 
{
    this.GenerateDefaultID = function() 
    {
        if (!plugins_quorum_Libraries_Game_Graphics_Material_.counter)
        {
            plugins_quorum_Libraries_Game_Graphics_Material_.counter = 1;
            return 1;
        }
        
        plugins_quorum_Libraries_Game_Graphics_Material_.counter++;
        return plugins_quorum_Libraries_Game_Graphics_Material_.counter;
    };
}
