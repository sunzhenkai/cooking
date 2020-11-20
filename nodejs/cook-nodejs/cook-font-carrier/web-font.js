const webfont = require("webfont").default;
webfont({
  files: "test.svg",
  fontName: "my-font-name"
})
  .then(result => {
    console.log(result);

    return result;
  })
  .catch(error => {
    throw error;
  });
