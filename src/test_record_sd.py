import sounddevice as sd
# print(sd.query_devices())
sd.default.device[0]=8
seconds=80
fs=441000
myrecording=sd.rec(int(seconds*fs),samplerate=fs,channels=2)

sd.wait()

from scipy.io.wavfile import write
write('./bgm.wav',fs,myrecording)