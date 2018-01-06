package ru.pks.pizzatime;

class Bonus {
    static void bonusItem(int Type, int item) {
        if (Type == 1) {
            RaphaelActivity.itemBonusRaphael = (int) Math.ceil(item / 3);
        }
    }
}
