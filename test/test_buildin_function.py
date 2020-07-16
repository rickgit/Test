import pytest
# https://docs.python.org/3/library/

''' 
Python/pythonrun.c#Py_Initialize()
Python/bltinmodule.c#builtin_methods()

abs()	delattr()	hash()	memoryview()	set()
all()	dict()	help()	min()	setattr()
any()	dir()	hex()	next()	slice()
ascii()	divmod()	id()	object()	sorted()
bin()	enumerate()	input()	oct()	staticmethod()
bool()	eval()	int()	open()	str()
breakpoint()	exec()	isinstance()	ord()	sum()
bytearray()	filter()	issubclass()	pow()	super()
bytes()	float()	iter()	print()	tuple()
callable()	format()	len()	property()	type()
chr()	frozenset()	list()	range()	vars()
classmethod()	getattr()	locals()	repr()	zip()
compile()	globals()	map()	reversed()	__import__()
complex()	hasattr()	max()	round()	

'''
def test_bdiType():
    assert  type(-1)==int
    assert  type(-1.)==float
    assert  type(11j)==complex

    assert  type('-1')==str

    assert type([1,2])==list
    assert type((1,2))==tuple
    assert type(range(10))==range

    assert type({'1':'s' })==dict


    assert type({1,2})==set
    assert type(frozenset({'j','l'}) )==frozenset

    

    assert  type(False)==bool
    assert  type(b"sdd")==bytes
    assert  type(bytearray(5))==bytearray
    assert  type(memoryview(bytes(6)))==memoryview
    print()

def test_bdiTypeCast():
    assert  type(int(1.1))==int
    assert  type(float(1))==float
    assert  type(complex(1))==complex

    assert  type(str(1))==str

    assert type(list('sdsd'))==list
    assert type(tuple([1,2]))==tuple
    assert type(range(10))==range

    assert type({'1':'s' })==dict


    assert type({1,2})==set
    assert type(frozenset({'j','l'}) )==frozenset

    

    assert  type(bool(1))==bool
    assert  bool(1) ==True

    assert  type(b"sdd")==bytes
    assert  type(bytearray(5))==bytearray
    assert  type(memoryview(bytes(6)))==memoryview
    print()
def test_buildinAbs():
    print()