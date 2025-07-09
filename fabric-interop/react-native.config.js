module.exports = {
  dependencies: {
    '@reactvision/react-viro': {
      platforms: {
        ios: {
          sourceDir: '../ios',
          folder: '../ios',
          podspecPath: '../ios/ViroReact.podspec',
        },
        android: {
          sourceDir: '../android',
          folder: '../android',
        },
      },
    },
  },
  // Enable CodeGen for TurboModule spec generation
  project: {
    ios: {},
    android: {},
  },
};