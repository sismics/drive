// This file is required by the index.html file and will
// be executed in the renderer process for that window.
// All of the Node.js APIs are available in this process.

const chokidar = require('chokidar');

chokidar.watch('node_modules2', {
  ignored: /[\/\\]\./,
  ignoreInitial: true
}).on('all', (event, path) => {
  console.log(event, path);
});