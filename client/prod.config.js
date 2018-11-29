const ScalaJS = require("./scalajs.webpack.config");
const Merge = require("webpack-merge");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

const path = require("path");
const rootDir = path.resolve(__dirname, "../../../..");
const resourcesDir = path.resolve(rootDir, "src/main/resources");

const WebApp = Merge(ScalaJS, {
    mode: "production",
    devtool: "inline-source-map",
    plugins: [new HtmlWebpackPlugin()]
});

module.exports = WebApp;
