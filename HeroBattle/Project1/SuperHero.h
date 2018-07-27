// OOP244 Workshop 7: Derived Classes
// File w7_athome.cpp
// Version 2.0
// Date 2017/13/12
// Author Jose Florencio Coelho Neto
// Description
//       This file is a header file with the functions declarations
/////////////////////////////////////////////////////

#ifndef SICT_SUPERHERO_H
#define SICT_SUPERHERO_H

namespace sict
{

    class SuperHero : public Hero
    {

        int m_bonusatq;
        int m_bonusdef;

    public:
        SuperHero();
		SuperHero(const char* name, int hp, int str, int atq, int def);
           
        int attackStrength() const;
        int defend() const;

		void setEmpty();     

    };

    const SuperHero& operator*(const SuperHero& first, const SuperHero& second);

}

#endif //W7_SUPERHERO_H
