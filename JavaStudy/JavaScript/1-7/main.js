/*・クラス名：Taiyaki
・インスタンス化する度に「あんこ」「クリーム」「チーズ」等と中身を変えられるように実装してください。
（コンストラクタで渡ってきた引数をセットする）
・「中身は〇〇です」と中身を出力する関数を作成してください。*/

class Taiyaki {

    constructor(food) {
        this.food = food;
    }
    FoodContent() {
        console.log('中身は' + this.food + 'です');
    }
}
let anko = new Taiyaki('あんこ');
anko.FoodContent();
let cream = new Taiyaki('クリーム');
cream.FoodContent();
let cheese = new Taiyaki('チーズ');
cheese.FoodContent();

