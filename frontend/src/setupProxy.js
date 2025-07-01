const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'https://8083-odsyjr2-…gitpod.io',
      changeOrigin: true,
      secure: false,
      ws: true,
    })
  );
};
