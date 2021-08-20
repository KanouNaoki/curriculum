
//問1：10、15、20、25を含む配列の変数scoresを用意して、画像のように偶数だけを出力してください。

let scores = [10, 15, 20, 25];
console.log(scores);

for (let i = 0; i < scores.length; i++) {
    if (scores[i] % 2 === 0) {
        console.log(scores+"偶数です");
    }
}
//問2：ガソリンとナンバーのプロパティを持った「carオブジェクト」を作成して、ガソリンとナンバーを出力してください。
let car = {
    gass: 20.5,
    num: 1234,
};
console.log(car);
console.log('ガソリンは' + car.gass + 'です');
console.log('ナンバーは' + car.num + 'です');