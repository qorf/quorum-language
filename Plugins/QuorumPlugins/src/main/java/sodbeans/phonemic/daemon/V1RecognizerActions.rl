%%{
machine phonemic;

# Return the voice to the user.
action getCurrentVoice {
    SpeechVoice v = textToSpeech.getCurrentVoice();

    if (v == null)
        response = "null";
    else
        response = v.toString();
}

# Speak a string.
action speak {
    // speak() is a fairly common call, so we'll ignore it.
    textToSpeech.speak(textToSpeak.toString(), priority, requestType);
}

# Speak a string (blocking)
action speakBlocking {
    boolean phonemicResult = textToSpeech.speakBlocking(textToSpeak.toString(), priority, requestType);
    response = Boolean.toString(phonemicResult);
}

# Stop all speech.
action stop {
    boolean phonemicResult = textToSpeech.stop();
    response = Boolean.toString(phonemicResult);
}

# Set the voice
action setVoice {
    // We currently only support english.
    SpeechVoice voice = new SpeechVoice(voiceName.toString(), SpeechLanguage.ENGLISH_US);
    boolean phonemicResult = textToSpeech.setVoice(voice);
    response = Boolean.toString(phonemicResult);
}

# Get all available voices
action getAvailableVoices {
    Iterator<SpeechVoice> voices = textToSpeech.getAvailableVoices();

    // Send a string to the client with each voice followed by the newline
    // character "\n".
    StringBuilder voicesString = new StringBuilder();
    while (voices.hasNext()) {
        voicesString.append(voices.next().toString());
        voicesString.append('\n');
    }

    response = voicesString.toString();
}

# Pause speech.
action pause {
    boolean phonemicResult = textToSpeech.pause();
    response = Boolean.toString(phonemicResult);
}

# Resume speech.
action resume {
    boolean phonemicResult = textToSpeech.resume();
    response = Boolean.toString(phonemicResult);
}

# Get and set volume.
action getVolume {
    response = Double.toString(textToSpeech.getVolume());
}

action setVolume {
    double vol = Double.parseDouble(decimalString.toString());
    boolean phonemicResult = textToSpeech.setVolume(vol);
    response = Boolean.toString(phonemicResult);
}

# Get and set speed.
action getSpeed {
    response = Double.toString(textToSpeech.getSpeed());
}

action setSpeed {
    double speed = Double.parseDouble(decimalString.toString());
    boolean phonemicResult = textToSpeech.setSpeed(speed);
    response = Boolean.toString(phonemicResult);
}

# Get and set pitch.
action getPitch {
    response = Double.toString(textToSpeech.getPitch());
}

action setPitch {
    double pitch = Double.parseDouble(decimalString.toString());
    boolean phonemicResult = textToSpeech.setPitch(pitch);
    response = Boolean.toString(phonemicResult);
}

# Is the engine speaking?
action isSpeaking {
    response = Boolean.toString(textToSpeech.isSpeaking());
}

# Is speech enabled?
action isSpeechEnabled {
    response = Boolean.toString(textToSpeech.isSpeechEnabled());
}

# Enable speech.
action enableSpeech {
    textToSpeech.setSpeechEnabled(true);
}

# Disable speech.
action disableSpeech {
    textToSpeech.setSpeechEnabled(false);
}

# Can the engine...
action canBlock {
    boolean phonemicResult = textToSpeech.canBlock();
    response = Boolean.toString(phonemicResult);
}

action canPause {
    boolean phonemicResult = textToSpeech.canPause();
    response = Boolean.toString(phonemicResult);}

action canResume {
    boolean phonemicResult = textToSpeech.canResume();
    response = Boolean.toString(phonemicResult);}

action canSetPitch {
    boolean phonemicResult = textToSpeech.canSetPitch();
    response = Boolean.toString(phonemicResult);
}

action canSetSpeed {
    boolean phonemicResult = textToSpeech.canSetSpeed();
    response = Boolean.toString(phonemicResult);
}

action canSetVoice {
    boolean phonemicResult = textToSpeech.canSetVoice();
    response = Boolean.toString(phonemicResult);
}

action canSetVolume {
    boolean phonemicResult = textToSpeech.canSetVolume();
    response = Boolean.toString(phonemicResult);
}

action canStop {
    boolean phonemicResult = textToSpeech.canStop();
    response = Boolean.toString(phonemicResult);
}

# Get the name of the current engine.
action getTextToSpeechEngine {
    TextToSpeechEngine currentEngine = textToSpeech.getTextToSpeechEngine();
    response = currentEngine.toString();
}

# Set the text to speech engine.
action setTextToSpeechEngine {
    boolean phonemicResult = textToSpeech.setTextToSpeechEngine(engine);
    response = Boolean.toString(phonemicResult);
}

# Get a list of available text to speech engines.
action getAvailableEngines {
    Iterator<TextToSpeechEngine> engines = textToSpeech.getAvailableEngines();

    // Send a string to the client with each engine followed by the newline
    // character "\n".
    StringBuilder enginesString = new StringBuilder();
    while (engines.hasNext()) {
        enginesString.append(engines.next().toString());
        enginesString.append('\n');
    }

    response = enginesString.toString();
}

# Reinitialize the engine.
action reinitialize {
    textToSpeech.reinitialize();
}

# Respeak the last spoken text.
action respeak {
    boolean phonemicResult = textToSpeech.respeak();
    response = Boolean.toString(phonemicResult);
}

# Get the phonemic version.
action getVersion {
    response = Double.toString(TextToSpeechFactory.PHONEMIC_VERSION);
}

}%%