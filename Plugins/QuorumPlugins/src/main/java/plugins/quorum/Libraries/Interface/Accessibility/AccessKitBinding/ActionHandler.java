package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    Receives VoiceOver's action requests. Called on the main thread while
    AppKit answers VoiceOver (inside glfwPollEvents), sometimes with
    AccessKit's tree borrowed: the handler must only remember the request
    and carry it out later. Updating AccessKit from here would end the
    program (a Rust panic, and AccessKit is built with panic = abort).
*/
public interface ActionHandler {
    public void requestAction(ActionRequest request);
}
