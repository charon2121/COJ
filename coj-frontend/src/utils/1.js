let people = [
  { name: 'John', age: 25 },
  { name: 'Jane', age: 20 },
  { name: 'Jack', age: 27 }
];

let newPeople = people.filter(function (item, index, arr) {
  return item.age >= 25;
})

console.log(newPeople)
/**
 *  [ { name: 'John', age: 25 }, { name: 'Jack', age: 27 } ]
 */