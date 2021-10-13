package plugins.quorum.Libraries.Interface.Vibration;

import quorum.Libraries.Interface.Vibration.VibrationArray_;

public interface VibrationStrategy {
    public void QuickPulse(double seconds);

    public void QuickPulseForever();

    public void SlowPulse(double seconds);

    public void SlowPulseForever();

    public void Rumble(double seconds);

    public void RumbleForever();

    public void Knock(int repetitions);

    public void KnockOnce();

    public void KnockForever();

    public void Vibrate(double seconds);

    public void VibrateForever();

    public void Vibrate(double seconds, double intensity);

    public void VibrateForever(double intensity);

    public void Vibrate(VibrationArray_ commandArray, int repetitions);

    public void VibrateOnce(VibrationArray_ commandArray);

    public void VibrateForever(VibrationArray_ commandArray);

    public void VibrateAtFrequency(double seconds, double frequency);

    public void VibrateAtFrequencyForever(double frequency);

    public void Stop();
}
