import shutil
import os


name = "df4d_roof_e"  # 示例名称

# 定义源文件路径
src_paths = [
    #"src/main/resources/assets/qr/blockstates/c70_left_end_board.json",

    "src/main/resources/assets/qr/models/block/df4d/c70_left_end_board.json",

    #"src/main/resources/assets/qr/models/item/c70_left_end_board.json",

    #"src/main/resources/data/qr/loot_tables/blocks/c70_left_end_board.json"
    
    #"src/main/resources/data/qr/recipes/c70_left_end_board.json"
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