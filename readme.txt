Github path : balajipilla/CitySearchService 

Environments used:
1) Java8
2) Jersey rest library
3) Json library
4) Tomcat
5) Eclipse

	Algorithm and Data structure : I thought about 2 solutions,

1) 	Using sorted list and saving pointers from where words with a character start in the list 
   	or positions of all distinct prefixes upto 3 characters and the search can be started 
   	based on the prefix provided. 
   
   	The insertions to the list will not be efficient when cities has to be added.
   	Also in worst case we will have to search the entire list.
   
2)  Using Trie like structure to store the information and a list to maintain the connections with child nodes.
    I have created a class TrieNode to do this. Each node will have one character as value, except the root node which will be empty.

    When a new city needs to be entered, the root node will check if the first character already exists 
	in its child nodes, if not will create a new node with the same value and it as a child node.
	The same process is followed to store the remaining characters in the word..

	The time taken to insert or search is constant. 

TODO : 	I am using a treemap to store the child nodes, to improve the performance of getting values, 
		we can use a binary search on the keyset of the treemap.

Services : I have created 2 services.

1) 	InitiateApplicationService : To generate the TrieNode data structure and cached the result.
    TODO : Better to start this service on load, so that the first request will not take more time.
    As the result is cached all requests except the first one will be fsater.
    
2) 	CitySearchService : Uses the response from InitiateApplicationService 
   	and searches for the cities based on the provided prefix and count.

