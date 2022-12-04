package test;


public class Tile {
    public final char letter;
    public final int score;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    public static class Bag {
        int[] quantity;
        int[] originalQuantity;
        Tile[] tiles;

        private static Bag b;

        private Bag() {
            this.quantity = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4,
                    2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            originalQuantity = quantity.clone();
            this.tiles = new Tile[]{new Tile('A', 1),
                    new Tile('B', 3), new Tile('C', 3),
                    new Tile('D', 2), new Tile('E', 1),
                    new Tile('F', 4), new Tile('G', 2),
                    new Tile('H', 4), new Tile('I', 1),
                    new Tile('J', 8), new Tile('K', 5),
                    new Tile('L', 1), new Tile('M', 3),
                    new Tile('N', 1), new Tile('O', 1),
                    new Tile('P', 3), new Tile('Q', 10),
                    new Tile('R', 1), new Tile('S', 1),
                    new Tile('T', 1), new Tile('U', 1),
                    new Tile('V', 4), new Tile('W', 4),
                    new Tile('X', 8), new Tile('Y', 4),
                    new Tile('Z', 10)
            };
        }

        public Tile getRand() {
            // Generating a number between 0-25.
            if (size() == 0)
                return null;
            int random = (int) (Math.random() * 26 + 1) - 1;

            //Checking what tile can be returned.
            while (this.quantity[random] == 0)
                random = (int) (Math.random() * 26 + 1) - 1;

            // Decreasing the amount of tiles, return ref to tile.
            this.quantity[random]--;
            return this.tiles[random];
        }

        public Tile getTile(char l) {
            if (size() == 0)
                return null;
            Tile c;
            for (int i = 0; i < this.tiles.length; i++) {
                c = this.tiles[i];
                if (c.letter == l && this.quantity[i] > 0) {
                    this.quantity[i]--;
                    return c;
                }
            }
            return null;
        }

        public void put(Tile t1) {
            if (size() == 98)
                return;
            int i, index = 0;

            for (i = 0; i < this.tiles.length; i++)
                if (t1.letter == this.tiles[i].letter)
                    index = i;
            if (this.quantity[index] < this.originalQuantity[index]) {
                this.quantity[index]++;
            }
        }

        public int size() {
            int size = 0;
            for (int quan : this.quantity)
                size += quan;
            return size;
        }

        public int[] getQuantities() {
            return this.quantity.clone();
        }

        // SingleTone
        public static Bag getBag() {
            if (b == null)
                b = new Bag();
            return b;
        }
    }
}

