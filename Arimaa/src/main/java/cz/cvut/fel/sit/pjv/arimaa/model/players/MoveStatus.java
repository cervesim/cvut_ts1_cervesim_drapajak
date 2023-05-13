package cz.cvut.fel.sit.pjv.arimaa.model.players;

public enum MoveStatus {
    DONE {
        @Override
        boolean isDone() {
            return true;
        }
    },
    ILLEGAL_MOVE {
        @Override
        boolean isDone() {
            return false;
        }
    };
    abstract boolean isDone();
}
