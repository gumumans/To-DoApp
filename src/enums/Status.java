package enums;

import entities.Task;
import exceptions.StatusException;

public enum Status implements State {
    DONE {
        @Override
        public void changeStatusInProgress(Task task) throws StatusException {
            throw new StatusException();
        }

        @Override
        public void changeStatusDone(Task task) throws StatusException {
            throw new StatusException();
        }

        @Override
        public void changeTaskDescription(Task task) throws StatusException {
            throw new StatusException("Нельзя поменять описание у завершенной задачи");

        }

        @Override
        public boolean deleteTask(Task task) throws StatusException {
            throw new StatusException("Нельзя удалить завершенную задачу");
        }
    }, IN_PROGRESS {
        @Override
        public void changeStatusInProgress(Task task) throws StatusException {
            throw new StatusException();
        }

        @Override
        public void changeStatusDone(Task task) throws StatusException {
            task.setStatusObj(DONE);
            System.out.println("Статус у задачи " + task.getTitle() + " сменён на \"Завершенную\"");
        }

        @Override
        public void changeTaskDescription(Task task) throws StatusException {
            throw new StatusException("Нельзя изменить описание");
        }

        @Override
        public boolean deleteTask(Task task) throws StatusException {
            throw new StatusException("Нельзя удалить задачу которая выполняется");

        }
    }, NEW {
        @Override
        public void changeStatusInProgress(Task task) throws StatusException {
            task.setStatusObj(IN_PROGRESS);
            System.out.println("Статус у задачи " + task.getTitle() + " сменён на \"В процессе\"");
        }

        @Override
        public void changeStatusDone(Task task) throws StatusException {
            throw new StatusException();
        }

        @Override
        public void changeTaskDescription(Task task) throws StatusException {
            System.out.println("Описание успешно изменено");
        }

        @Override
        public boolean deleteTask(Task task) throws StatusException {
            System.out.println("Задача удалена!");
            return true;
        }
    };
}


interface State {
    void changeStatusInProgress(Task task) throws StatusException;

    void changeStatusDone(Task task) throws StatusException;

    void changeTaskDescription(Task task) throws StatusException;

    boolean deleteTask(Task task) throws StatusException;
}
