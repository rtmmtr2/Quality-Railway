#!/usr/bin/env python3
"""
生成 38kg / 43kg 轨道的 blockstate。
输出格式与手工维护的现有文件一致：展开 turn 和 waterlogged 所有组合。
用法:
    python gen_track_blockstates.py
"""

import json
import os

MOD_ID = "qr"
OUT_DIR = f"src/main/resources/assets/{MOD_ID}/blockstates"

# shape -> (模型名, y旋转)。y=0 时不写 "y"。
# 43kg 是基准，38kg 通过 overrides 覆盖差异。
BASE_SHAPES = {
    "none":   ("air", 0),
    "zo":     ("x_ortho", 90),
    "xo":     ("x_ortho", 0),
    "pd":     ("diag", 0),
    "nd":     ("diag_2", 0),
    "an":     ("ascending", 180),
    "as":     ("ascending", 0),
    "ae":     ("ascending", 270),
    "aw":     ("ascending", 90),
    "tn":     ("teleport", 180),
    "ts":     ("teleport", 0),
    "te":     ("teleport", 270),
    "tw":     ("teleport", 90),
    "cr_o":   ("cross_ortho", 0),
    "cr_d":   ("cross_diag", 0),
    "cr_pdx": ("cross_d1_xo", 0),
    "cr_pdz": ("cross_d1_zo", 0),
    "cr_ndx": ("cross_d2_xo", 0),
    "cr_ndz": ("cross_d2_zo", 0),
}

# 每个重量级的配置
WEIGHTS = {
    "38kg": {
        "prefix": "standard/38kg/tie_formal",
        "overrides": {
            "zo": ("z_ortho", 0),   # 38kg 用 z_ortho，无 y
            "nd": ("diag2", 0),     # 38kg 用 diag2，43kg 用 diag_2
        },
    },
    "43kg": {
        "prefix": "standard/43kg/tie_formal",
        "overrides": {},            # 直接用 BASE_SHAPES
    },
}


def build_variants(weight: str, cfg: dict) -> dict:
    prefix = cfg["prefix"]
    overrides = cfg["overrides"]
    variants = {}

    for turn in (False, True):
        for waterlogged in (False, True):
            for shape, (default_model, default_y) in BASE_SHAPES.items():
                model_name, y = overrides.get(shape, (default_model, default_y))
                if model_name == "air":
                    model = "minecraft:block/air"
                else:
                    model = f"{MOD_ID}:block/track/{prefix}/{model_name}"

                key = f"shape={shape},turn={'true' if turn else 'false'},waterlogged={'true' if waterlogged else 'false'}"
                entry = {"model": model}
                if y != 0:
                    entry["y"] = y
                variants[key] = entry

    return {"variants": variants}


def main():
    os.makedirs(OUT_DIR, exist_ok=True)
    for weight, cfg in WEIGHTS.items():
        data = build_variants(weight, cfg)
        path = os.path.join(OUT_DIR, f"track_standard_{weight}.json")
        with open(path, "w", encoding="utf-8") as f:
            json.dump(data, f, indent=2, ensure_ascii=False)
            f.write("\n")
        print(f"wrote {path}  ({len(data['variants'])} variants)")


if __name__ == "__main__":
    main()