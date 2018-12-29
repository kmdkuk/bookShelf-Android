# What this?

Andoroidのアプリ開発練習用に本棚アプリを作成したい所存

## commit message
コミットにはemojiprefixを使いましょう

テンプレートは`.commit_template`を参考に
これをgit-configで登録します．

## labelについて
以下の定義でがんばります．(とりあえず参考サイト様のものを引用)

- Story
> スクラムで言うところのストーリーとなるIssueに付けます。
> このラベルが付いたIssueにのみ、後述のEstimateを、計画時にストーリーポイントとして設定します。
これの集合体がProduct Backlog
長くても半日でできるものにする．でかすぎる場合はepicとしてまとめる．

- Task
> ストーリーと言うPBI(Product Backlog Items)を実現するための、作業の実行単位です。つまりタスクです。
> コメントからは、ストーリーのIssueをリンクしておきます。
これの集合体(スプリント期間にやるものがスプリントバックログ)

- Chore
> Pivotal Trackerでもある、直接的な価値にはつながらないけれど必要な雑務、に付けます。
> ドキュメントの整備等があると思います。

- Spike
> あるストーリーやタスクに着手するために、事前に行う必要があるIssueに付けます。
> 先行的な、技術的調査などが該当します。

- Discussion
> 議論スレとなるIssueに付けます。
> 仕様や実装方針など、積極的な議論を前提としたIssueにはこのラベルを付けます。

## 参考サイト様

[公式チュートリアル](https://developer.android.com/training/basics/firstapp/?hl=ja)  
[mixiさんのチュートリアル](http://mixi-inc.github.io/AndroidTraining/)
[GitHubのテンプレート](https://qiita.com/suzuki-hoge/items/3a568dff36fd981082ba)  
開発の流れの参考に
[その１](https://dev.classmethod.jp/project-management/remote-work-with-github/)
[その２](https://dev.classmethod.jp/etc/github-discussion-sidebar/)
[スクラムについてのQiitaまとめ](https://qiita.com/kamesennin/items/955e0c14a63cc140267e)
[emoji prefixについて](https://goodpatch.com/blog/beautiful-commits-with-emojis/)
