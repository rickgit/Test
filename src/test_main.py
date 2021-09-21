## 顺序执行
print('hello python\n')

## 内置变量，【__main__】作为脚本执行；【文件名】作为模块被调用
print('入口函数 ',__name__,'\n')

def printFun():
    print("func")

if __name__=='__main__':
    print('文件作为脚本执行')
    printFun()

