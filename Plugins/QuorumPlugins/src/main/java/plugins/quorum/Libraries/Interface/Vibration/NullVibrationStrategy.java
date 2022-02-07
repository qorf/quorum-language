package plugins.quorum.Libraries.Interface.Vibration;

import quorum.Libraries.Interface.Vibration.VibrationArray_;

public class NullVibrationStrategy implements VibrationStrategy {
    @Override
    public void QuickPulse(double seconds) {
    }

    @Override
    public void QuickPulseForever() {

    }

    @Override
    public void SlowPulse(double seconds) {

    }

    @Override
    public void SlowPulseForever() {

    }

    @Override
    public void Rumble(double seconds) {

    }

    @Override
    public void RumbleForever() {

    }

    @Override
    public void Knock(int repetitions) {

    }

    @Override
    public void KnockOnce() {

    }

    @Override
    public void KnockForever() {

    }

    @Override
    public void Vibrate(double seconds) {

    }

    @Override
    public void VibrateForever() {

    }

    @Override
    public void Vibrate(double seconds, double intensity) {

    }

    @Override
    public void VibrateForever(double intensity) {

    }

    @Override
    public void Vibrate(VibrationArray_ commandArray, int repetitions) {

    }

    @Override
    public void VibrateOnce(VibrationArray_ commandArray) {

    }

    @Override
    public void VibrateForever(VibrationArray_ commandArray) {

    }

    @Override
    public void VibrateAtFrequency(double seconds, double frequency) {

    }

    @Override
    public void VibrateAtFrequencyForever(double frequency) {

    }

    @Override
    public void Stop() {

    }
}
