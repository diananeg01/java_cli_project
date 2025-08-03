javac -d out src/main/java/org/task/cli/*.java

cd out

java org.task.cli.TaskCLI add "buy groceries"
java org.task.cli.TaskCLI list

java org.task.cli.TaskCLI update 1 "Buy groceries and cook dinner"
java org.task.cli.TaskCLI delete 1

java org.task.cli.TaskCLI mark-in-progress 1
java org.task.cli.TaskCLI mark-done 1

java org.task.cli.TaskCLI list

java org.task.cli.TaskCLI list done
java org.task.cli.TaskCLI list todo
java org.task.cli.TaskCLI list in-progress