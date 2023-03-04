import numpy as np
import matplotlib.pyplot as plt

def plot_graph(x, y):
    plt.plot(x, y)
    plt.xlabel('X Axis')
    plt.ylabel('Y Axis')
    plt.title('Graph')
    plt.savefig('graph.png')
    plt.close()
