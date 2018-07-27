// OOP244 Workshop 7: Derived Classes
// File w7_athome.cpp
// Version 2.0
// Date 2017/13/12
// Author Jose Florencio Coelho Neto
// Description
//       This file is a source code for the functions
/////////////////////////////////////////////////////

#include <iostream>
#include <cstring>
#include "Hero.h"
#include "SuperHero.h"

namespace sict
{

	SuperHero::SuperHero()
	{
		setEmpty();
	}

	SuperHero::SuperHero(const char* name, int hp, int str, int atq, int def) : Hero(name, hp, str)
	{
		if (atq > 0 && def > 0)
		{
		
			m_bonusatq = atq;
			m_bonusdef = def;
			
		}

		else
		{
			setEmpty();
		}

	}

	
	void SuperHero::setEmpty()
	{
		
		m_bonusatq = 0;
		m_bonusdef = 0;

	}

	int SuperHero::attackStrength() const
	{
		if (Hero::attackStrength() != 0) return (Hero::attackStrength() + m_bonusatq);
		else return 0;
	
	}

	int SuperHero::defend() const
	{
		if (Hero::attackStrength() != 0) return m_bonusdef;
		else return 0;

	}

	const SuperHero& operator*(const SuperHero& first, const SuperHero& second)
	{
		SuperHero tmp_fst = first, tmp_sec = second;

		int round = 0;
		for (; round <= max_rounds && tmp_fst.isAlive() && tmp_sec.isAlive(); round++)
		{
			tmp_sec -= (tmp_fst.attackStrength() - tmp_sec.defend());
			tmp_fst -= (tmp_sec.attackStrength() - tmp_fst.defend());
		}

		if (tmp_sec.isAlive())
		{
			std::cout << "Super Fight! " << first << " vs " << second << " : Winner is " << second << " in " << round << " rounds." << std::endl;
			return second;
		}

		else
		{
			std::cout << "Super Fight! " << first << " vs " << second << " : Winner is " << first << " in " << round << " rounds." << std::endl;
			return first;
		}

	}

}