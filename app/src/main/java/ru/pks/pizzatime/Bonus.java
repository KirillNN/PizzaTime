package ru.pks.pizzatime;

class Bonus {
    static void bonusItem(int Type, int item) {
        if (Type == 1) {
            RaphaelActivity.itemBonusRaphael_1 = (int) Math.ceil(item / 3);
        }
    }
}
