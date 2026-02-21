const API_BASE_URL='/user/api/users';

export const loginUser = async (email,password) => {
  try{
    console.log("Sending login request:", { email });
    const credentials =btoa(`${email}:${password}`);

    const response = await fetch(`${API_BASE_URL}/roleUser`, { //damn I hate JS 
      method: 'GET',
      headers: {
        //'Content-Type': 'application/json',
        'Authorization':`Basic ${credentials}`,
      },
      //body: JSON.stringify({email, password}),
    });
    
    if (response.status == 200) {
      const userData = await response.json();
      //localStorage.setItem("user", JSON.stringify(userData.user)); // includes role
      return {success: true, data: userData};
      localStorage.setItem("email", email);
      localStorage.setItem("password", password);
    } else {
      const errorData = await response.text();
      return {success:false, data: errorData};
    }
  } catch (error){
    console.error("API encountered an error lol:", error);
    return {success:false, message: "Something went wrong"}
  }
};

export const registerUser = async (email, name, password) => {  //this is why it didn't want to take success status 
  try{
    const response = await fetch(`${API_BASE_URL}/register`, {
      method: "POST",
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({email,name,password}),
    });

    if (response.ok) {
      const userData = await response.json();
      return {success: true, data: userData};  // for some reason it doesn't wanna take success lmao 
    } else {
      const errorData = await response.json();
      return {success:false, data: errorData};
    }
  } catch(error) {
    console.error("API encountered an error lol (register edition):", error);
    return {success:false, message: "Something went wrong"};
  }
};
