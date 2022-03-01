$(function() {

	$('select[multiple].active.3col').multiselect({
	  columns: 3,
	  placeholder: 'Select Toppings',
	  search: true,
	  searchOptions: {
	      'default': 'Search Toppings'
	  },
	  selectAll: true
	});

});