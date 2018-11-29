const ScalaJS = require("./scalajs.webpack.config");
const Merge = require("webpack-merge");
const HtmlWebpackPlugin = require("html-webpack-plugin");

const WebApp = Merge(ScalaJS, {
    plugins: [
        new HtmlWebpackPlugin({
            template: 'index_dev.html'
        })
    ]
});

module.exports = WebApp;
