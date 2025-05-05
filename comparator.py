import subprocess
import re
import time

# 准备执行 Java 程序的命令
command = ["java", "Main"]  # 假设你已经编译了 Main.java 并生成了 Main.class

# 使用 subprocess 运行 Java 程序并捕获输出
try:
    while True:  # 循环进行对比，直到手动停止
        # 运行 Java 程序并捕获输出
        result = subprocess.run(command, capture_output=True, text=True, check=True)

        # 获取 Java 程序的标准输出（即路径信息）
        java_output = result.stdout
        print("Java Output:")
        print(java_output)

        # 提取 Java 程序输出中的 Total Distance
        dp_distance = None
        brute_distance = None
        
        # 使用正则表达式查找 Total Distance for Bitmask DP and Brute Force
        dp_match = re.search(r"Shortest route \(Bitmask DP\):.*Total distance:\s*(\d+) miles", java_output)
        brute_match = re.search(r"Shortest route \(Brute Force\):.*Total distance:\s*(\d+) miles", java_output)
        
        if dp_match:
            dp_distance = int(dp_match.group(1))  # 获取 Bitmask DP 的距离
        if brute_match:
            brute_distance = int(brute_match.group(1))  # 获取 Brute Force 的距离

        # 打印提取的距离
        print(f"Bitmask DP Route Distance: {dp_distance} miles")
        print(f"Brute Force Route Distance: {brute_distance} miles")

        # 比较两个路径的总距离
        if dp_distance == brute_distance:
            print("The distances match!")
        else:
            print("The distances do not match!")

        # 延时1秒后继续下一轮对比
        time.sleep(1)

except KeyboardInterrupt:
    print("\nComparison stopped manually by user.")
