import 'package:app_flutter/base/BaseState.dart';
import 'package:app_flutter/components/Toast.dart';
import 'package:app_flutter/initial/App.dart';
import 'package:app_flutter/pages/user/login/LoginViewModel.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'initial/App.dart';

class Todo {
  const Todo(this.title, this.description);

  final String description;
  final String title;
}
void main() {
  // Navigator.push(context, route)
  runApp(
    // MultiProvider(providers: [],child: ProviderWidget(),)
    // ProviderWidget()
    createApp()
    // materialApp(),
  );
  Toast.toastConf();
}
class ProviderWidget extends StatefulWidget {
  const ProviderWidget({Key? key}) : super(key: key);

  @override
  State<ProviderWidget> createState() => _ProviderWidgetState();
}

class _ProviderWidgetState extends BaseState<ProviderWidget,LoginViewModel> {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<LoginViewModel>(create: ((context) {
     return mViewModel;
    }),child: MaterialApp(home: LoginWidget()));
  }
  
  @override
  LoginViewModel initViewModel() {
     return LoginViewModel();
  }
}
class LoginWidget extends StatelessWidget {
  const LoginWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    context.read<LoginViewModel>();
    return Text(context.read<LoginViewModel>().stateShowSecure.toString(),);
  }
}

MaterialApp materialApp() {
  return MaterialApp(
    title: 'Passing Data',
    home: TodosScreen(
      todos: List.generate(
        20,
        (i) => Todo(
          'Todo $i',
          'A description of what needs to be done for Todo $i',
        ),
      ),
    ),
  );
}

class TodosScreen extends StatelessWidget {
  const TodosScreen({Key? key, required this.todos}) : super(key: key);

  final List<Todo> todos;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Todos'),
      ),
      body: ListView.builder(
        itemCount: todos.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(todos[index].title),
            // When a user taps the ListTile, navigate to the DetailScreen.
            // Notice that you're not only creating a DetailScreen, you're
            // also passing the current todo through to it.
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => DetailScreen(todo: todos[index]),
                ),
              );
            },
          );
        },
      ),
    );
  }
}

class DetailScreen extends StatelessWidget {
  // In the constructor, require a Todo.
  const DetailScreen({Key? key, required this.todo}) : super(key: key);

  // Declare a field that holds the Todo.
  final Todo todo;

  @override
  Widget build(BuildContext context) {
    // Use the Todo to create the UI.
    return Scaffold(
      appBar: AppBar(
        title: Text(todo.title),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Text(todo.description),
      ),
    );
  }
}
