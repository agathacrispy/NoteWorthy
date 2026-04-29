"""
Extracts a pitches.csv from a vocals WAV file using librosa's pyin algorithm.

Usage:
    python extract_pitches.py <path_to_vocals.wav> <output_dir>

Example:
    python extract_pitches.py songs/never-gonna-give-you-up/vocals.wav songs/never-gonna-give-you-up

Requirements:
    pip install librosa numpy
"""

import sys
import math
import csv
import librosa
import numpy as np

HOP_LENGTH = 512


def hz_to_midi(hz):
    if hz <= 0 or np.isnan(hz):
        return None
    return round(69 + 12 * math.log2(hz / 440.0))


def extract(vocals_path, output_dir):
    audio, sr = librosa.load(vocals_path, mono=True)

    f0, voiced_flag, _ = librosa.pyin(
        audio,
        fmin=librosa.note_to_hz("C2"),
        fmax=librosa.note_to_hz("C7"),
        sr=sr,
        hop_length=HOP_LENGTH,
    )

    rows = []
    for i, (freq, voiced) in enumerate(zip(f0, voiced_flag)):
        if not voiced:
            continue
        midi = hz_to_midi(freq)
        if midi is None:
            continue
        timestamp_ms = round(i * HOP_LENGTH / sr * 1000)
        rows.append((timestamp_ms, midi))

    out_path = f"{output_dir}/pitches.csv"
    with open(out_path, "w", newline="") as f:
        writer = csv.writer(f)
        for timestamp_ms, midi in rows:
            writer.writerow([timestamp_ms, midi])

    print(f"Wrote {len(rows)} pitch frames to {out_path}")


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python extract_pitches.py <vocals.wav> <output_dir>")
        sys.exit(1)
    extract(sys.argv[1], sys.argv[2])
