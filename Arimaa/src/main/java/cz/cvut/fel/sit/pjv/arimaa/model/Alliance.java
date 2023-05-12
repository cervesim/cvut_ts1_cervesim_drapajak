package cz.cvut.fel.sit.pjv.arimaa.model;

/**
 *
 */
public enum Alliance {
    SILVER {
        @Override
        public boolean isSilver() {
            return true;
        }

        @Override
        public boolean isGolden() {
            return false;
        }
    },
    GOLDEN {
        @Override
        public boolean isSilver() {
            return false;
        }

        @Override
        public boolean isGolden() {
            return true;
        }
    };

    public abstract boolean isSilver();
    public abstract boolean isGolden();
}
