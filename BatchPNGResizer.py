import os
from PIL import Image
import glob

def get_target_size():
    """获取用户输入的目标尺寸"""
    while True:
        try:
            width = int(input("请输入缩放后的宽度（像素px）: "))
            height = int(input("请输入缩放后的高度（像素px）: "))
            if width <= 0 or height <= 0:
                print("尺寸必须为正整数，请重新输入。")
                continue
            return (width, height)
        except ValueError:
            print("请输入有效的数字。")

def get_image_path():
    """获取图片路径"""
    while True:
        path = input("请输入图片文件夹路径: ").strip()
        if os.path.exists(path):
            return path
        else:
            print("路径不存在，请重新输入。")

def find_png_files(root_path):
    """查找所有PNG文件（包括子文件夹）"""
    png_files = []
    # 使用glob递归查找所有png文件
    pattern = os.path.join(root_path, "**", "*.png")
    png_files = glob.glob(pattern, recursive=True)
    
    # 同时查找.PNG扩展名（大写）
    pattern_upper = os.path.join(root_path, "**", "*.PNG")
    png_files.extend(glob.glob(pattern_upper, recursive=True))
    
    return png_files

def resize_image(image_path, target_size):
    """缩放单张图片并覆盖原图"""
    try:
        # 打开图片
        with Image.open(image_path) as img:
            # 缩放图片
            resized_img = img.resize(target_size, Image.LANCZOS)
            
            # 保存图片（覆盖原图）
            resized_img.save(image_path, "PNG")
            return True
    except Exception as e:
        print(f"处理图片 {image_path} 时出错: {e}")
        return False

def main():
    """主函数"""
    print("=== 图片缩放器 ===")
    print("注意：此程序将直接覆盖原图，建议先备份重要图片！")
    
    # 获取目标尺寸
    target_size = get_target_size()
    print(f"目标尺寸: {target_size[0]}x{target_size[1]} 像素")
    
    # 获取图片路径
    image_path = get_image_path()
    
    # 查找所有PNG文件
    print("正在查找PNG文件...")
    png_files = find_png_files(image_path)
    
    if not png_files:
        print("在指定路径下未找到PNG文件。")
        return
    
    print(f"找到 {len(png_files)} 个PNG文件")
    print("开始处理图片...")
    
    # 处理每个图片
    success_count = 0
    for i, file_path in enumerate(png_files, 1):
        print(f"处理中 ({i}/{len(png_files)}): {os.path.basename(file_path)}")
        
        if resize_image(file_path, target_size):
            success_count += 1
    
    print(f"\n处理完成！成功处理 {success_count}/{len(png_files)} 个文件")

if __name__ == "__main__":
    main()