# author anshu.wang
# time 2021年9月21日 19点32分

import os,sys
import shutil#拓展os，拷贝文件
import json #键值对序列化

def printFun():
    args=sys.argv#[test_drawable_copy.py, ]
    print('参数列表: ',len(args)," : ",str(args))
    wspath=None
    if len(args) ==1 :
        wspath=input("输入包含res的文件夹： ")
    else:
        wspath=args[1]
    if wspath == '':
        print('结束：\n输入文件夹： ',str(wspath))
        return
    srcPath=os.path.join(str(wspath),'res')
    print("输入文件夹",str(srcPath))

    targetPath=os.path.join(wspath,'res-gen')
    drawables=['drawable','drawable-mdpi','drawable-hdpi','drawable-xhdpi','drawable-xxhdpi','drawable-xxxhdpi']
    targetDrawableIndex=2
    srcDrawablePath=str(os.path.join(srcPath,drawables[targetDrawableIndex]))
    if os.path.exists(srcDrawablePath)==False:
        print('drawable找不到')  
        return  
    if os.path.exists(targetPath)==False:# 删除res-gen文件夹
        os.makedirs(targetPath)
    else:
        shutil.rmtree(targetPath)
        os.makedirs(targetPath)
    files = os.listdir(str(srcDrawablePath))
    map={}
    if(os.path.exists(os.path.join(wspath,'srcmap.json'))):
        map=json.load(open(os.path.join(wspath,'srcmap.json'),'r'))
        print('map的值 ',os.path.join(wspath,'srcmap.json')," ",str(map) )
    for drawableFile in files:# 遍历基准文件
        if drawableFile.endswith('.png')==False:
            continue
        info='重命名为：'
        if map.get(drawableFile,'')!='':
            info='重命名为：('+map.get(drawableFile,'')+")"

        # map[drawableFile]=input(info)+'.png'
        print('map的值 '+map.get(drawableFile,''))

 
        for drawableName in drawables:# 遍历drawalbe类别文件
            copyDrawablePath=os.path.join(srcPath,drawableName)
            if os.path.exists(copyDrawablePath)==False:#判断drawable类别文件夹是否存在
                continue
            copyDrawableFile=os.path.join(copyDrawablePath,drawableFile)# 判断drawable类别文件下，是否存在拷贝的图片
            if os.path.exists(copyDrawableFile)==False:
                continue
            targetDrawablePath=os.path.join(targetPath,drawableName)
            if os.path.exists(targetDrawablePath)==False:# 创建目标drawable文件夹文件
                os.makedirs(targetDrawablePath)
            srcfilePath=str(os.path.join(srcPath,drawableName,drawableFile))
            targetfilePath=str(os.path.join(targetDrawablePath,map[drawableFile]))
            print('开始拷贝',str(srcfilePath),str(targetfilePath))
            shutil.copyfile(str(srcfilePath),str(targetfilePath))
    json.dump(map,open(os.path.join(wspath,'srcmap.json'),'w'))

if __name__=='__main__':
    printFun()

