public class Words 
{

	private String word;
	protected int wordSize;
	
	public Words(int num)
	{
		this.word = arrayOfWords(num);
	}
	
	public String getWord()
	{
		return word;
	}
	public int getWordSize()
	{
		return wordSize;
	}
	public void setWord(int num)
	{
		this.word = arrayOfWords(num);
	}
	
	public void setWordSize(String[] myWords)
	{
		wordSize = myWords.length;
	}
	
	public String arrayOfWords(int num)
	{	
		String[] myWords = new String[]{"Hello","Ackee","Miami","England","Stephen"};
		setWordSize(myWords);
		String word = myWords[num];
		return word;
	}
	
	public static void print(Object obj)
	{
		System.out.println(obj);
	}
	
	public static void hints(int num)
	{
		
		switch(num)
		{
			case 1:
					print("The first word of a two word fictional Japenese cat");
					break;
			case 10:
					print("One of Lionel Richie biggest song");
					break;
			case 100:
					print("One of the most common word used in a conversation");
					break;
			case 1000:
					print("A famous song sung by Adele");
					break;
			case 10000:
					print("An informal way of greeting");
					break;
			case 2:
					print("It's a Fruit");
					break;
			case 20:
					print("It can be eaten raw but within the caribbean it is always cooked");
					break;
			case 200:
					print("Makes a fine meal with breadfruit and saltfish");
					break;
			case 2000:
					print("It is sold in stores in a can");
					break;
			case 20000:
					print("Helps form the National Dish in Jamaica");
					break;
			case 3:
					print("A well known City");
					break;
			case 30:
					print("Has one of the most beautiful beaches in a developed country");
					break;
			case 300:
					print("It is found in the US");
					break;
			case 3000:
					print("It is known as the playboy paradise to all the party people");
					break;
			case 30000:
					print("It's called the sunshine state");
					break;
			case 4:
					print("It's a developed country");
					break;
			case 40:
					print("The famous computer scientist Tim Berners-Lee who inventing the World Wide Web was born in this country");
					break;
			case 400:
					print("Soccer Teams such as Manchester United, Liverpool,Arsenal and Chelsea are found within this country");
					break;
			case 4000:
					print("This country is the birthplace for both Shakespeare and the Beatles");
					break;
			case 40000:
					print("The capital city of _______ is London");
					break;
			case 400000:
					print("The birthplace of UEFA");
					break;
			case 4000000:
					print("The famous actor Daniel Craig was born in this country");
					break;
			case 5:
					print("This person is a famous American author");
					break;
			case 50:
					print("This word is the author first name");
					break;
			case 500:
					print("The person is a male author");
					break;
			case 5000:
					print("Most of his novels if not all are terrifying");
					break;
			case 50000:
					print("The first name of this author was originated from the Bible");
					break;
			case 500000:
					print("This supernatural fictional author novels have contributed greatly to TV channels such as Scifi(Syfy) where his books became alive by film makers");
					break;
			case 5000000:
					print("His last name is King");
					break;
			default:
					print("Sorry you ran out of guesses");
		
		}
	}
}

