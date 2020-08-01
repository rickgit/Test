## 两种类型：
application packages
library packages

### 其他地方引用：
import 指定一个库命名空间中的内如如何在另一个库中使用。
// Import only foo.
import 'package:lib1/lib1.dart' show foo;

// Import all names EXCEPT foo.
import 'package:lib2/lib2.dart' hide foo;

import 'package:greetings/hello.dart' deferred as hello;


### 如何组织库的源文件。
如何使用 export 命令。
何时使用 part 命令。
何时使用 library 命令


