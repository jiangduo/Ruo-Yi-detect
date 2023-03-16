# 文件名：plot.py
import matplotlib.pyplot as plt
import io
import base64


def generate_plot():
    x = [1, 2, 3, 4, 5]
    y = [1, 4, 9, 16, 25]

    # 绘制折线图
    plt.plot(x, y)

    # 将图像数据转换为Base64编码字符串
    buffer = io.BytesIO()
    plt.savefig(buffer, format='png')
    buffer.seek(0)
    img_str = base64.b64encode(buffer.read()).decode()

    # 返回Base64编码的图像字符串
    return img_str
