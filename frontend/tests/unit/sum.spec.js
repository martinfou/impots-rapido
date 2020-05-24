const sum = require('./sum');

test('test sum(1,2) gives 3', () => {
  expect(sum(1, 2)).toBe(3);
});