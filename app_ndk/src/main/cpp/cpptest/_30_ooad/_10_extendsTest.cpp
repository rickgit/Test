//
// Created by anshu-pc on 2020/7/31.
//
#include <string>

#include "_10_extendsTest.h"

using namespace std;

class ParentClass {
protected:
    string zone;
public:
    void dosomething() {

    }

    string getZone() {
        return zone;
    }
    void setZone(string zone) {
        this->zone=zone;
    }
};

class ChildClass : public ParentClass {

};

void _10_extendsTest::testExtends() {
    ChildClass childClass;
    string zone = childClass.getZone();
}

class ChildOpOverrideClass: public ParentClass {
public:
    ChildOpOverrideClass operator+(const ChildOpOverrideClass & b){
        ChildOpOverrideClass c;
        c.setZone(this->zone+b.zone);
        return c;
    }

};

void testOperation() {
    ChildOpOverrideClass child1;
    ChildOpOverrideClass child2;
    ChildOpOverrideClass aClass = child1 + child2;
}