import shutil
import os


name = "gq70_tank_c"  # 示例名称

# 定义源文件路径
src_paths = [
    # 第一个文件路径
    "src/main/resources/assets/qr/blockstates/c70_left_end_board.json",
    # 第二个文件路径
    "src/main/resources/assets/qr/models/block/gq70/c70_left_end_board.json",
    # 第三个文件路径
    "src/main/resources/assets/qr/models/item/c70_left_end_board.json"
]

for src in src_paths:
    try:
        # 构建目标路径
        dest = os.path.join(os.path.dirname(src), f"{name}.json")

        # 执行文件复制
        shutil.copy(src, dest)
        print(f"Success: {src} -> {dest}")

    except FileNotFoundError:
        print(f"Error: Source file not found - {src}")
    except Exception as e:
        print(f"Error copying {src}: {str(e)}")