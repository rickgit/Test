//
// Created by anshu-pc on 2020/7/31.
//

#include <assert.h>
#include "_00_class.h"
class Metarial{
private:
    int size;
public:
    void dosometion(){
        //
    }
    static void showClassname(){
        //printclass name
    }
    void setSize(int size){
        this->size=size;
    }
    int getSize(){
        return size;
    }

    static int countref;
};
void _00_class::testClass() {
    Metarial metarial ;
    metarial.setSize(1);
    assert(metarial.getSize()==1);
    //静态方法
    Metarial::countref=32;
    Metarial::showClassname();
}