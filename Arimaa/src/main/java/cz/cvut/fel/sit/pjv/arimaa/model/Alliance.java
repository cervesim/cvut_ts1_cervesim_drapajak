package cz.cvut.fel.sit.pjv.arimaa.model;

import cz.cvut.fel.sit.pjv.arimaa.model.players.GoldenPlayer;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;
import cz.cvut.fel.sit.pjv.arimaa.model.players.SilverPlayer;

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

        @Override
        public Player choosePlayer(final GoldenPlayer goldenPlayer, final SilverPlayer silverPlayer) {
            return silverPlayer;
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

        @Override
        public Player choosePlayer(final GoldenPlayer goldenPlayer, final SilverPlayer silverPlayer) {
            return goldenPlayer;
        }
    };

    public abstract boolean isSilver();
    public abstract boolean isGolden();
    public abstract Player choosePlayer(final GoldenPlayer goldenPlayer, final SilverPlayer silverPlayer);
}

