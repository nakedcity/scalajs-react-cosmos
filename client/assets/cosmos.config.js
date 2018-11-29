module.exports = {
    fileMatch: [
        '**/__fixture?(s)__/clientstories-{fastopt,fullopt}.{js,jsx,ts,tsx}',
        // '**/?(*.)fixture?(s).{js,jsx,ts,tsx}'
    ],
    webpack: (config, {env}) => {
        // Return customized config

        config.module.rules.push(
            {
                test: /\.js$/,
                use: ["source-map-loader"],
                enforce: "pre"
            }
        )
        return {
            ...config
        };
    }
};