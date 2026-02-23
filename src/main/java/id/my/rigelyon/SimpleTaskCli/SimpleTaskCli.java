package id.my.rigelyon.SimpleTaskCli;

public class SimpleTaskCli {
    static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: SimpleTaskCli <command> [argument]");
            System.exit(1);
        }
        TaskManager taskManager = new TaskManager();
        String command = args[0];
        switch (command) {
            case "help":
                if (args.length < 2) {
                    String helpPage = """
                             Usage: SimpleTaskCli <command> [argument]
                            \s
                             Simple app to track your tasks.
                            \s
                             Commands:
                                 help                Show this help page
                                 list                Show available tasks
                                 add                 Add new task
                                 update              Edit task description
                                 delete              Remove task
                                 mark-in-progress    Mark task to in-progress
                                 mark-done           Mark task to done
                                \s
                             Examples:
                                 SimpleTaskCli add "Feed the cat"
                                 SimpleTaskCli update 2 "Do the laundry"
                                 SimpleTaskCli delete 3
                                 SimpleTaskCli mark-in-progress 1
                                 SimpleTaskCli mark-done 4
                                \s
                                 SimpleTaskCLi list done
                                 SimpleTaskCli list todo
                                 SimpleTaskCli list in-progress
                            \s""";
                    System.out.println(helpPage);
                }
                break;
            case "list":
                if (args.length < 2) {
                    System.err.println("""
                             Usage: SimpleTaskCli list [status]
                            \s
                             Status:
                                 done            Finished task
                                 todo            Newly created task
                                 in-progress     Task that are still in-progress
                            \s""");
                    System.exit(1);
                }
                taskManager.showTasks();
                break;
            case "add":
                if (args.length < 2) {
                    System.err.println("Usage: SimpleTaskCli add [description]");
                    System.exit(1);
                }
                taskManager.addTask(args[1]);
                break;
            case "update":
                if (args.length < 3) {
                    System.err.println("Usage: SimpleTaskCli update [task-id] [description]");
                    System.exit(1);
                }
                try {
                    int index = Integer.parseInt(args[1]);
                    taskManager.editTask(index);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid index");
                    System.exit(1);
                }
                break;
            case "delete":
                if (args.length < 2) {
                    System.err.println("Usage: SimpleTaskCli delete [task-id]");
                    System.exit(1);
                }
                taskManager.deleteTask(Integer.parseInt(args[1]));
                break;
            case "mark-in-progress":
                if (args.length < 2) {
                    System.err.println("Usage: SimpleTaskCli mark-in-progress [task-id]");
                    System.exit(1);
                }
                break;
            case "mark-in-done":
                if (args.length < 2) {
                    System.err.println("Usage: SimpleTaskCli mark-in-done [task-id]");
                    System.exit(1);
                }
                break;
            default:
                System.err.println("Unknown command: " + command);
                System.exit(1);
        }
        taskManager.saveTasks();
    }
}
